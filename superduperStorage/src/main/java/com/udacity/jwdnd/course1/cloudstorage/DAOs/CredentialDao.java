package com.udacity.jwdnd.course1.cloudstorage.DAOs;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.pojos.Credential;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CredentialDao {

    @Resource
    private CredentialMapper credentialMapper;

    public int addCredential(Credential credential) {
        return credentialMapper.insert(credential);
    }

    public Credential[] getCredentialListings(int userId) {
        return credentialMapper.getCredentialListings(userId);
    }

    public Credential getCredential(int credentialId) {
        return credentialMapper.getCredential(credentialId);
    }

    public void deleteCredential(int credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public void updateCredential(Credential credential) {
        credentialMapper.updateCredential(credential);
    }

    public Credential getCredential(String url, String username){
        return credentialMapper.getCredentialByUrlAndUsername(url, username);
    }
}
