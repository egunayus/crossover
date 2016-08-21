package com.crossover.techtrial.api.rest.booking;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.api.util.UserUtility;
import com.crossover.techtrial.domain.model.booking.FlightTicket;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightTicketRepository;
import com.crossover.techtrial.domain.service.booking.FlightTicketService;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping("/flightTicket")
public class FlightTicketRestController {

	@Autowired
	UserUtility userUtility;
	
	@Autowired
	FlightTicketRepository flightTicketRepository;
	
	@Autowired
	FlightTicketService flightTicketService;
	
	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value="/checkin", method=RequestMethod.POST)
	public FlightTicket checkin(@RequestBody FlightTicket flightTicketRequest) {
		User user = userUtility.getCurrentUser();
		
		return flightTicketService.checkin(flightTicketRequest, user);
	}
	
	@RequestMapping(value="/asPdf/{id}", method=RequestMethod.GET)
	public ResponseEntity getTicketAsPDF(@PathVariable("id") Long id, HttpServletResponse response) {
		
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
		
        ByteArrayOutputStream baos = null;
        
        try (InputStream inputStream = JRLoader.getResourceInputStream("report/ticket.jrxml")) {
        	JasperReport jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));

        	// load sql query from file
        	byte[] queryData = Files.readAllBytes(new ClassPathResource("report/ticket.sql_template").getFile().toPath());
        	String query = new String(queryData);
        	
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
        	
            HashMap<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRResultSetDataSource(rs));
            
            baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            
            byte[] reportBytes = baos.toByteArray();
            
            return ResponseEntity
                    .ok()
                    .contentLength(reportBytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "filename=ticket.pdf")
                    .body(reportBytes);            
        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
        	
        }
		
        return null;
	}
	
}
