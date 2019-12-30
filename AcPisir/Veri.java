package com.iremipek.AcPisir;

import androidx.cardview.widget.CardView;

//Firebaseden çekilecek olan veriler için bir datamodel.

public class Veri {
    private String isim;
    private String profilePic;
    private CardView cardView;

    public Veri() {
    }

    public Veri(String isim, String profilePic) {
        this.isim = isim;
        this.profilePic = profilePic;
        this.cardView = cardView;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
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
