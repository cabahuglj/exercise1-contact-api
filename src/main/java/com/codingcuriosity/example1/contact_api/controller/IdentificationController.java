package com.codingcuriosity.example1.contact_api.controller;

import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactRes;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import com.codingcuriosity.example1.contact_api.service.IdentificationService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class IdentificationController {

  @Resource
  IdentificationService identificationService;

  @GetMapping(value = "/getAll/simple")
  public List<Identification> getIdentification() {
    return identificationService.findAll();
  }

  @GetMapping(value = "/getAll")
  public List<Contact> getIdentificationDetailed() {
    return identificationService.findAllDetailed();
  }

  @PostMapping(value = "/add")
  public ContactRes createIdentification(@RequestBody Contact dat) {
    return identificationService.insertIdentification(dat);
  }

  @PutMapping(value = "/update/{contactId}")
  public void updateIdentification(@PathVariable(value = "contactId") String contactId,
      @RequestBody Identification dat) {
    identificationService.updateIdentification(contactId, dat);
  }

  @DeleteMapping(value = "/delete/{contactId}")
  public void deleteIdentification(@PathVariable(value = "contactId") String contactid) {
    identificationService.deleteIdentification(contactid);
  }

  @DeleteMapping(value = "/deleteAll")
  public void deleteAllIdentification() {
    identificationService.deleteAllIdentification();
  }
}
