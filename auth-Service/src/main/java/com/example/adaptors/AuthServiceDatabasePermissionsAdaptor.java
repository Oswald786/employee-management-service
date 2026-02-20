package com.example.adaptors;

import com.example.Mapper.PermissionsMapper;
import com.example.entities.PermissionsEntity;
import com.example.models.PermissionCreationRequestModel;
import com.example.models.PermissionsModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class AuthServiceDatabasePermissionsAdaptor {

    @Inject
    EntityManager entityManager;

    @Inject
    PermissionsMapper permissionsMapper;

    @Inject
    public AuthServiceDatabasePermissionsAdaptor(){}

    Logger logger = LoggerFactory.getLogger(AuthServiceDatabasePermissionsAdaptor.class);

    public void createPermission(PermissionCreationRequestModel permissionsToCreate){
        PermissionsModel permissionsModel = new PermissionsModel(null, permissionsToCreate.getPermissionName(),
                permissionsToCreate.getDescription());
        entityManager.persist(permissionsMapper.toEntity(permissionsModel));
        entityManager.flush();
        logger.info("Permission Created Successfully " + " Name: " + permissionsModel.getPermissionName());
    }

    public Optional<PermissionsModel> retrievePermissionById(Integer permissionId){
        PermissionsEntity result = entityManager.find(PermissionsEntity.class, permissionId);
        logger.info("Finding permission using Id " + " Permission Id: " + permissionId);
        PermissionsModel mappedResult = permissionsMapper.toModel(result);
        return Optional.ofNullable(mappedResult);
    }

    public Optional<PermissionsModel> retrievePermissionByName(String permissionName){
        TypedQuery<PermissionsEntity> query = entityManager.createQuery("select p from PermissionsEntity p where p.permissionName = :permissionName", PermissionsEntity.class);
        query.setParameter("permissionName", permissionName);
        logger.info("Finding permission using Name " + " Permission Name: " + permissionName);
        List<PermissionsEntity> result = query.getResultList();
        if(result.isEmpty()){
            logger.warn("Permission not found Returning empty optional");
            return Optional.empty();
        } else if (result.size() > 1) {
            logger.error("More than one permission found with same name. Returning empty optional");
            return Optional.empty();
        } else {
            logger.info("Permission found");
            return Optional.ofNullable(permissionsMapper.toModel(result.get(0)));
        }
    }

    public void updatePermissionDescription(Integer permissionId, String description){
        PermissionsEntity permissionEntity = entityManager.find(PermissionsEntity.class, permissionId);
        permissionEntity.setDescription(description);
        entityManager.merge(permissionEntity);
        entityManager.flush();
        logger.info("Permission Description Updated Successfully " + " Permission Id: " + permissionId + " Description: " + description);
    }

    public void deletePermission(Integer permissionId){
        PermissionsEntity permissionEntity = entityManager.find(PermissionsEntity.class, permissionId);
        entityManager.remove(permissionEntity);
        entityManager.flush();
        logger.info("Permission Deleted Successfully " + " Permission Id: " + permissionId);
    }

    public void deleteAllPermissions(){
         int deletedCount = entityManager.createQuery("DELETE FROM PermissionsEntity p").executeUpdate();
        logger.info("All Permissions Deleted Successfully");
        logger.info("Deleted {} Permissions", deletedCount);
    }

}
