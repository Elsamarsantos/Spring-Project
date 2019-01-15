package  io.altar.pharmaFriend.repositories;



import java.util.List;

import org.springframework.stereotype.Repository;

import  io.altar.pharmaFriend.models.Pharmacy;

@Repository
public class PharmacyRepository extends EntityRepository<Pharmacy> {
	
	
	PharmacyRepository() {}
	
	@Override
	protected Class<Pharmacy> getEntityClass() {
		
		return Pharmacy.class;
	}


	protected String getNamedQuery() {
		return Pharmacy.QUERYLOCATION;
	}
	@Override
	protected String getNamedQueryAll() {
		return Pharmacy.QUERY_ALL;
	}
	
	protected String getNamedQuery2() {
		return Pharmacy.QUERYNAME;
	}

	public Pharmacy getPharmacyByName(String pharmacyName) {
		return em.createNamedQuery(Pharmacy.QUERYNAME, Pharmacy.class).setParameter("pharmacyName", pharmacyName).getSingleResult();
		
	}
	
	public Pharmacy getPharmacyByLocation(double lon, double lat) {
		return em.createNamedQuery(Pharmacy.QUERYLOCATION, Pharmacy.class).setParameter("lonLocation", lon).setParameter("latLocation", lat).getSingleResult();
		
	}
	

	public long getBiggestId() {
		
		long biggestId = 0;
		
		if (em.createNamedQuery(Pharmacy.QUERY_ALL, Pharmacy.class).getResultList().size() > 0) {
			biggestId =(long) em.createNamedQuery(Pharmacy.QUERY_BIGGEST_F).getSingleResult();
		};
		
		return biggestId;
	}
	public List<Pharmacy> getPharmacytest(String name, String dose) {
		return em.createNamedQuery(Pharmacy.QUERY_TEST, Pharmacy.class).setParameter("name", name).setParameter("dose", dose).getResultList();
		
	}
}
