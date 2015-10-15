package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Author : Sheeban Shaikh
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    @Column
    private String password;

    @Column
    private String location;

    @Column
    private String timeLineDetails;

    @Column
    private String totalFollowers;

    public String getTotalFollowers() {
        return totalFollowers;
    }

    public void setTotalFollowers(String totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeLineDetails() {
        return timeLineDetails;
    }

    public void setTimeLineDetails(String timeLineDetails) {
        this.timeLineDetails = timeLineDetails;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public User(String password, String location, String timeLineDetails, String totalFollowers) {
        this.password = password;
        this.location = location;
        this.totalFollowers = totalFollowers;
        this.timeLineDetails = timeLineDetails;
    }

    public User() {
    }

    public class UserBuilder {
        private String password;
        private String location;
        private String timeLineDetails;
        private String totalFollowers;

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public UserBuilder setTimeLineDetails(String timeLineDetails) {
            this.timeLineDetails = timeLineDetails;
            return this;
        }

        public UserBuilder setTotalFollowers(String totalFollowers) {
            this.totalFollowers = totalFollowers;
            return this;
        }

        public User createUser() {
            return new User(password, location, timeLineDetails, totalFollowers);
        }
    }


}
