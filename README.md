# Get-area-info-thorugh-pincode

This is a Area Detail Generator app through Pin code :

******* make sure to give internet permission in android manifest file *******

              <uses-permission android:name="android.permission.INTERNET"/>
              

    i) In the activity_main.xml -> EditText field : to take input from user i.e Pin code.
                                Button : to start action.
                                textView : to print area details.
                                
    ii) In the activity_main.java -> I took the pin code given by the user and use a requestQueue to make request with the help of volley library.
                                     And crated a jsonObjectRequest to make request to the url( http://www.postalpincode.in/api/pincode/ + PinCode)
                                     and on response created a json Array and if status is succesfull then created json Object and convert array 
                                     to json object and print the data to textview
                                     
                                     
                                     
I have used this api -> http://www.postalpincode.in/api/pincode/ for area details generator.

Note: this api covers only India region.
