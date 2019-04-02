package com.camellia.soorty.utills;


public interface DateAndTimePicker {
      void selectedDate(int strYear, int strMonth, int strDay, int requestCode);
      //  void selectedTime(int hrs, int mins, String am_pm);
      void selectedTime(int hrs, int mins, String am_pm, long milis);
}
