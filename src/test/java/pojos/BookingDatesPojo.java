package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    private String checkin;
    private String checkout;

    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "{checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }

    //"checkin": "2021-09-21",
    //"checkout": "2021-12-21"
}
