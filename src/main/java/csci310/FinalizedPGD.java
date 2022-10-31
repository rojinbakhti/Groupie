package csci310;

public class FinalizedPGD {

    public int groupdate_id;
    public int event_id;
    public int accepted_count;
    public int rejected_count;

    FinalizedPGD(int groupdate_id_,int event_id_,int accepted_count_,int rejected_count_){
        this.groupdate_id = groupdate_id_;
        this.event_id = event_id_;
        this.accepted_count = accepted_count_;
        this.rejected_count = rejected_count_;
    }
}
