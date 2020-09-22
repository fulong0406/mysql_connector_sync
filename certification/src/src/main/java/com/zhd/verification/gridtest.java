package com.zhd.verification;

public class gridtest {
    private int grid_id ;
    private String  diff_buf;

    public int getGrid_id() {
        return grid_id;
    }

    public void setGrid_id(int grid_id) {
        this.grid_id = grid_id;
    }

    public String getDiff_buf() {
        return diff_buf;
    }

    public void setDiff_buf(String diff_buf) {
        this.diff_buf = diff_buf;
    }

    @Override
    public String toString() {
        return "data{" +
                "grid_id=" + grid_id +
                ", diff_buf='" + diff_buf + '\'' +
                '}';
    }
}
