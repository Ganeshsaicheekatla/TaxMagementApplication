package com.CodingNinjas.TaxEase.service;

import com.CodingNinjas.TaxEase.dto.TaxRecordDto;
import com.CodingNinjas.TaxEase.exception.TaxRecordNotFoundException;
import com.CodingNinjas.TaxEase.model.TaxRecord;
import com.CodingNinjas.TaxEase.repository.TaxRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TaxRecordService {

    /*
        This is the service class for TaxRecord, you need to complete the class by doing the following:

            a. Use appropriate annotations.
            b. Complete the methods given below.
            c. Autowire the necessary dependencies.
     */



    @Autowired
    TaxRecordRepository taxRecordRepository;


    // This is the service method for the api which allows user to get a tax Record by sending the record id
    public TaxRecord getTaxRecordById(Long id) {

        try {
            TaxRecord taxRecord = taxRecordRepository.findById(id).get();
            return taxRecord;
        }
        catch (Exception e){
            throw new TaxRecordNotFoundException("Not found");
        }
    }


    // This is the service method for the api which allows user fetch all tax Records
    public List<TaxRecord> getAllRecords() {
        return taxRecordRepository.findAll();
    }


    // This is the service method for the api which allows user to create a tax Record by sending TaxRecordDto as the @ResponseBody
    public void createTaxRecord(TaxRecordDto taxRecordDto) {
        TaxRecord taxRecord = new TaxRecord();
        taxRecord.setUserName(taxRecordDto.getUserName());
        taxRecord.setTaxYear(taxRecordDto.getTaxYear());
        taxRecord.setIncome(taxRecord.getIncome());
        taxRecord.setDeductions(taxRecord.getDeductions());

        taxRecordRepository.save(taxRecord);
    }


    // This is the service method for the api which allows user to update a tax Record by sending the record id as a pathVariable and TaxRecordDto as a RequestBody
    public void updateTaxRecord(TaxRecordDto taxRecordDto, Long id) {

        TaxRecord existingRecord = getTaxRecordById(id);
        existingRecord.setUserName(taxRecordDto.getUserName());
        existingRecord.setTaxYear(taxRecordDto.getTaxYear());
        existingRecord.setIncome(taxRecordDto.getIncome());
        existingRecord.setDeductions(taxRecordDto.getDeductions());
        taxRecordRepository.save(existingRecord);


    }


    // This is the service method for the api which allows user to delete a tax Record by sending the record id as a pathVariable
    public void deleteTaxRecord(Long id) {
        taxRecordRepository.deleteById(id);
    }


    // This is the service method for the api which allows user to fetch all the tax Records by sending the username as a requestParam
    public List<TaxRecord> getRecordsByName(String userName) {
        return taxRecordRepository.findByUserName(userName);
    }


    // This is the service method for the api which allows user to approve a tax Record by sending the record id as a pathVariable
    public void approveTaxFiling(Long id) {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecord.setFilingApproved(true);
        taxRecordRepository.save(taxRecord);
    }


    // This is the service method for the api which allows user to reject a tax Record by sending the record id as a pathVariable
    public void rejectTaxFiling(Long id) {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecord.setFilingApproved(false);
        taxRecordRepository.save(taxRecord);
    }


}
