package com.reservation.chambre.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import com.google.common;
import com.reservation.chambre.exception.ResourceNotFoundException;
import com.reservation.chambre.model.Reservation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reservation.chambre.repository.ReservationRepository;
import com.reservation.chambre.service.ChambreService;
import com.reservation.chambre.service.ReservationService;

@CrossOrigin(origins = "https://projetstages.netlify.app")
@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService rs;   
    private final ChambreService cs;

    private final ReservationRepository rr;

    @PostMapping("/getPdf")
    public ResponseEntity<String> pdfDemande(@RequestBody Map<String, Object> requestMap) {
        log.info("ICI GENERATION PDF");
        try {

            String nom_complet = (String) requestMap.get("nom_complet");
            String adresse = (String) requestMap.get("adresse");
            String tel = (String) requestMap.get("tel");

            String fileName = nom_complet + getUUID();

            String data = "Nom et Prénom: " + nom_complet.toUpperCase() + "\n"
                    + "Adresse: " + adresse + "\n" + "Téléphone: " + tel;

            Document document = new Document();

            try {
                PdfWriter.getInstance(document, new FileOutputStream("/home/ordinateurs/Bureau/" + fileName + ".pdf"));

                document.open();
                setRectangleInpdf(document);

                Paragraph chunk = new Paragraph("E-Boisson Alcoolique\n\n", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray = getjsonArrayFromString((String) requestMap.get("details"));

                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, getMapFromJson(jsonArray.getString(i)));
                }

                document.add(table);

                Paragraph footer = new Paragraph("TOTAL : " +  String.valueOf((int)requestMap.get("somme")).concat(".0") + "Ariary\n\n" + "MERCI POUR VOTRE VISITE!", getFont("Data"));

                document.add(footer);

                document.close();
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>("{\"message\":\"PDF CRÉE\"}", HttpStatus.OK);

        } catch (JSONException e) {
        }
        return new ResponseEntity<>("{\"message\":\"ERREUR PDF\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/create/{numChambre}")
    public Reservation create(@RequestBody Reservation res, @PathVariable int numChambre) {
        // mettre à jour le status de la chambre
        cs.changeStatus(numChambre);
        return rs.create(res);
    }

    @GetMapping("/read")
    public List<Reservation> read() {
        return rs.read();
    }

    @GetMapping("/getReservationById/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        Reservation reservation = rr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not exist with id :" + id));
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/update/{id}")
    public Reservation update(@PathVariable int id, @RequestBody Reservation commande) {
        return rs.update(id, commande);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id) {
        return rs.delete(id);
    }
    
//    @DeleteMapping("/deleteCommandeBiClientId/{id}")
//    public int deleteCommandeByClientId(@PathVariable int id) {
//        return cs.deleteCommandeByClientId(id);
//    }


    public static String getUUID() {
        Date date = new Date();
        Long time = date.getTime();
        return "facture - n° -" + time;
    }

    private void setRectangleInpdf(Document document) throws DocumentException {
        log.info("DANS SET RECTANGLE IN PDF");
        Rectangle rect = new Rectangle(577, 825, 18, 15);
        rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBackgroundColor(BaseColor.LIGHT_GRAY);
        rect.setBorderWidth(1);
        document.add(rect);
    }

    private Font getFont(String type) {
        log.info("DANS GET FONT");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }

    }

    private void addTableHeader(PdfPTable table) {
        log.info("DANS ADD TABLE HEADER");
        Stream.of("DÉSIGNATION", "PRIX", "QUANTITÉ", "PRIX-TOTAL")
                .forEach((columnTitle) -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.BLUE);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    public static JSONArray getjsonArrayFromString(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        return jsonArray;
    }

    public static Map<String, Object> getMapFromJson(String data) {
        if (!Strings.isEmpty(data)) {
            return new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {
            }.getType());

        }
        return new HashMap<>();
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("DANS ADDROWS");
        table.addCell((String) data.get("libelle"));
        table.addCell(Double.toString((Double) data.get("prix")));
        table.addCell(Double.toString((Double) data.get("qte")));
        table.addCell(Double.toString((Double) data.get("prixTotal")));
    }

}
