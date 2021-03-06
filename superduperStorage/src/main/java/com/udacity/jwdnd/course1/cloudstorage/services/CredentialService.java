package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.DAOs.CredentialDao;
import com.udacity.jwdnd.course1.cloudstorage.pojos.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.securityservices.EncryptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CredentialService {

    @Resource
    private CredentialDao credentialDao;
    @Resource
    private EncryptionService encryptionService;

    public Credential[] getCredentials(int userId){
         return credentialDao.getCredentialListings(userId);
    }

    public Credential getCredential(int credentialId){
        return credentialDao.getCredential(credentialId);
    }

    public String deleteCredential(int credentialId){
        credentialDao.deleteCredential(credentialId);
        return "success";
    }

    public String createCredential(CredentialForm credentialForm, int userId){
        String result = "error";

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encodedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);


        Credential credential = new Credential();
        credential.setUserid(userId);
        credential.setUrl(credentialForm.getUrl());
        credential.setUserName(credentialForm.getUserName());
        credential.setKey(encodedKey);
        credential.setPassword(encodedPassword);

        if(credentialDao.getCredential(credential.getUrl(), credential.getUserName()) == null){
            int creId = credentialDao.addCredential(credential);
            result = creId > 0? "success": "error";
        } else{
            credentialDao.updateCredential(credential);
            result = "success";
        }
        return result;
    }

}
