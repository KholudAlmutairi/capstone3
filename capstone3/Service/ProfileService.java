package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ProfileDTO;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Model.Profile;
import com.example.capstone3.Repository.CustomerRepository;
import com.example.capstone3.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final CustomerRepository customerRepository;

    public List<Profile> getAllProfiles() {

        return profileRepository.findAll();
    }


    public void addProfiler(ProfileDTO profileDTO){
        Customer customer=customerRepository.findCustomersById(profileDTO.getCustomer_id());
        if(customer==null){
            throw new ApiException("Customer not found");
        }
         Profile profile =new Profile(null,profileDTO.getAge(),profileDTO.getEmail(),profileDTO.getPhoneNumber(),profileDTO.getDistrict(),profileDTO.getNationalAddress(),customer);
         profileRepository.save(profile);
    }



    public void updateProfile(ProfileDTO profileDTO) {
        Profile profile=profileRepository.findProfileById(profileDTO.getCustomer_id());

        if (profile == null) {
            throw new ApiException("Profile not found");
        }
        profile.setAge(profileDTO.getAge());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setDistrict(profileDTO.getDistrict());
        profile.setNationalAddress(profileDTO.getNationalAddress());

        profileRepository.save(profile);


    }

    public void deleteProfile(Integer id) {
        Profile p=profileRepository.findProfileById(id);
        if (p == null) {
            throw new ApiException("Wrong id");
        }
        profileRepository.delete(p);
    }


    //-----------------------   end crud   ------------------------------

//-----------------------   1 endpoint   ------------------------------


public Profile verifyPhoneNumberMatchesEmail(Integer customerId, String phoneNumber, String email) {
    Customer customer=customerRepository.findCustomersById(customerId);
    if (customer == null) {
        throw new ApiException("Customer not found");
    }

    Profile profile = customer.getProfile();
    if (profile == null) {
        throw new ApiException("Profile not found for this customer");
    }

    if (profile.getPhoneNumber().equals(phoneNumber) && profile.getEmail().equals(email)) {
        return profile;
    } else {
        return null;
    }
}}
//}
