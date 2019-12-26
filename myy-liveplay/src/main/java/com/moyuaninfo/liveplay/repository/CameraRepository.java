package com.moyuaninfo.liveplay.repository;

import com.moyuaninfo.liveplay.entity.Camera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;


public interface CameraRepository extends CrudRepository<Camera, Long> {

	Camera findByLink(String link);

	List<Camera> findAllByAreaid(Long areaid);

	@Query(value="SELECT * from myy_camera c where c.areaid in (?1) ",nativeQuery = true)
	List<Camera> findAllByAreaidIn(ArrayList<Long> areaid);
	
	Camera findByIp(String ip);
	
//	@Query(nativeQuery = true,value="SELECT t.`status`,count(t.`status`) from myy_camera t, laboratory l where l.id = t.laboratory and l.colleage = ?1 group by t.`status`")
//	List<Object> findCountOfCameraValidByColleage(Long colleageid);

	Camera findByAreaidAndIpAndChannel(Long areaid, String ip, String channel);

//	@Query(value = "select u.* from myy_camera u,laboratory i where u.laboratory=i.id and i.colleage= ?1 and u.status= ?2 ",nativeQuery = true)
//	List<Camera> findAllByAreaAndStatus(int colleageid, String status);

//	List<Camera> findByAreaAndStatusAndLinkstatus(long labid, String status, String linkstatus);

//	Camera deleteById(long id);

//	@Query(value = "select u.* from myy_camera u  where u.laboratory=i.id and i.colleage= ?1 and u.status= '1' and i.floor1=?2 ",nativeQuery = true)
//	List<Camera> findAllByLab(int colleageid, String buildname);

	Camera findByIpAndLinkAndAreaid(String ip, String link, long areaid);
}
