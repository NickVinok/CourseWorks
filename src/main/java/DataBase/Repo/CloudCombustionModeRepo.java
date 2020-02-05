package DataBase.Repo;

import DataBase.Model.CloudCombustionMode;
import DataBase.Model.CloudCombustionModeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudCombustionModeRepo extends JpaRepository<CloudCombustionMode, Long> {
    CloudCombustionMode finByCloudCombustionKey(CloudCombustionModeKey key);
}
