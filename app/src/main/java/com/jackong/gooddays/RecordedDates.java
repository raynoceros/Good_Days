package com.jackong.gooddays;

/**
 * Created by User on 6/3/2017.
 */
public class RecordedDates {

    private int _id;
    private String _datename;
    private String _date;
    private int _repeateveryyear;
    private int _days;


    public RecordedDates(){

    }

    public RecordedDates(String _datename, String _date, int _repeateveryyear, int _days) {
        this._datename = _datename;
        this._date = _date;
        this._repeateveryyear = _repeateveryyear;
        this._days = _days;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_datename(String _datename) {
        this._datename = _datename;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
    public void set_repeateveryyear(int _repeateveryyear) {
        this._repeateveryyear = _repeateveryyear;
    }

    public void set_days(int _days) {
        this._days = _days;
    }

    public int get_id() {
        return _id;
    }

    public String get_datename() {
        return _datename;
    }

    public String get_date() {
        return _date;
    }

    public int get_repeateveryyear() {
        return _repeateveryyear;
    }

    public int get_days() {
        return _days;
    }
}

