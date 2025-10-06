
package xyz.dxstoolnavigation.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import xyz.dxstoolnavigation.pojo.Feedback;

@Mapper
public interface FeedbackMapper {

    @Insert("insert into feedback values (#{name},#{id},#{email},#{content})")
    void addFeedback(Feedback feedback);

}
