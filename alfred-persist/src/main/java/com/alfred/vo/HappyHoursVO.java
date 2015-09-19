package com.alfred.vo;
import java.util.List;
import com.alfred.model.HappyHoursDetails;

public class HappyHoursVO {
	
	
	    private Integer id;

	    private String happyHoursName;

	    private Integer isActive;
	    
	    private List<HappyHoursDetails> happyHoursDetailsList;

		public Integer getId() {
			return id;
		}

		public String getHappyHoursName() {
			return happyHoursName;
		}

		public Integer getIsActive() {
			return isActive;
		}

		public List<HappyHoursDetails> getHappyHoursDetailsList() {
			return happyHoursDetailsList;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setHappyHoursName(String happyHoursName) {
			this.happyHoursName = happyHoursName;
		}

		public void setIsActive(Integer isActive) {
			this.isActive = isActive;
		}

		public void setHappyHoursDetailsList(
				List<HappyHoursDetails> happyHoursDetailsList) {
			this.happyHoursDetailsList = happyHoursDetailsList;
		}
	    
	    
	  
}
