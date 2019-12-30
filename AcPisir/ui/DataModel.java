package com.iremipek.AcPisir.ui;

//Tab menu için oluşturulan dataModel

public class DataModel {
    private String isim;
    private String profilePic;

    public DataModel() {
    }

    public DataModel(String isim, String profilePic) {
        this.isim = isim;
        this.profilePic = profilePic;
    }


    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
