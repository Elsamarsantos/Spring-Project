package  io.altar.pharmaFriend.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import  io.altar.pharmaFriend.Dtos.PharmacyDto;
import  io.altar.pharmaFriend.models.Medicine;
import  io.altar.pharmaFriend.models.Pharmacy;
import io.altar.pharmaFriend.repositories.PharmacyRepository;

@Component
public class UserRequestBusiness {
	@Inject
	PharmacyBusiness pharmacyBusiness1;
	@Inject
	MedicineBusiness medicineBusiness1;
	@Inject
	PharmacyRepository pharmacyRepository1; 
	
	@Transactional
	public List<PharmacyDto> userRequest(String name,String dose,String volumeUnit,double lon, double lat, double distance) {
		
		Medicine medicine =medicineBusiness1.consultMedicineWithoutDto(name, dose, volumeUnit);
		
		Iterator<Pharmacy> listpharmacy= pharmacyBusiness1.getTheNeartsPharmacy(lon,lat,distance).iterator();
		
		List<Pharmacy> listToAdd =new ArrayList<>();
		
		while(listpharmacy.hasNext()) {
			
			Pharmacy pharmacy =listpharmacy.next();
			Iterator <Medicine> listMedicineInPharmacy=pharmacy.getListStock().iterator();
			
			while(listMedicineInPharmacy.hasNext()) {
				
				
			if(listMedicineInPharmacy.next().getId().equals(medicine.getId())) {
				listToAdd.add(pharmacy);
				
			}	
			}
		}
		
		
		Iterator<Pharmacy> listpharmacy1= listToAdd.iterator();
		
		List<PharmacyDto> listToAddDto =new ArrayList<>();
		
		
		while (listpharmacy1.hasNext()) {
			Pharmacy pharmacy1 = listpharmacy1.next();

			PharmacyDto pharmacyDto = new PharmacyDto(pharmacy1.getId(),pharmacy1.getPharmacyName(),pharmacy1.getaddress(),pharmacy1.getLonLocation(),pharmacy1.getLatLocation());
			listToAddDto.add(pharmacyDto);
		}

	
		return listToAddDto;

	}
	
	@Transactional
	public List<PharmacyDto> userRequest(String name,String dose,double userLon, double userLat, double userdistance) {
		
		
			
			Iterator <Pharmacy> pharmacyList = pharmacyRepository1.getPharmacytest(name, dose).iterator();
			
			List <Pharmacy> nearestList = new ArrayList<Pharmacy>() ;
			
			while (pharmacyList.hasNext()) {
				
				Pharmacy pharmacyToAdd = pharmacyList.next();
				
				NearLocationBusiness userlocation = new NearLocationBusiness(userLon,userLat);
				
				NearLocationBusiness pharmacy = new NearLocationBusiness(pharmacyToAdd.getLonLocation(),pharmacyToAdd.getLatLocation());
				
				double distance= pharmacy.distanceTo(userlocation);
				
				if(distance<userdistance) {
					nearestList.add(pharmacyToAdd);
				}		
			}
			
			
		

	return pharmacyBusiness1.transformInToDto(nearestList);
	
	}
}
