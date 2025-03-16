package com.example.personaldiary;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEntryActivity extends AppCompatActivity {


    private Button dateButton;
    private Button timeButton;
    private TextView selectedDateTextView;
    private TextView selectedTimeTextView;
    private Button submit;
    private TextView title;
    private EditText description;
    private DBHandler dbHandler;
    private Context context;

    // For adding an image
    private Button imageButton;
   // private ImageView imageView;
    private String imageDirectory = null;
    private int SELECT_PICTURE = 200;
    private ActivityResultLauncher<String> imagePickerLauncher;

//    private Button imageRemoveButton;
//    private boolean hasImage=false;

    private String formattedDate="";
    private String currentTime="";
    private String heading="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        context = this;
        dbHandler = new DBHandler(context);

        // Get current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = dateFormat.format(currentDate);

        // Get the current time
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Format the time
        currentTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);

        heading= formattedDate.concat(" ").concat(currentTime);

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
//                            System.out.println("Entetfrhdhdfgdfhg" + result);
                            imageDirectory = result.toString();
                            // Handle the selected image URI
      //                      imageView.setImageURI(result);
                        }
                    }
                });

        dateButton = findViewById(R.id.dateButton);
        selectedDateTextView = findViewById(R.id.date);
        timeButton = findViewById(R.id.timeButton);
        selectedTimeTextView=findViewById(R.id.time);
        submit=findViewById(R.id.submitEntryButton);
        title=findViewById(R.id.textView6);
        description=findViewById(R.id.descriptionEditText);
        imageButton = findViewById(R.id.imageButton);
//        imageRemoveButton=findViewById(R.id.imageRemoveButton);

        title.setText(heading);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showTimePickerDialog();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString();
                String newDesc = description.getText().toString();
                String newDate=selectedDateTextView.getText().toString();
                String newTime=selectedTimeTextView.getText().toString();



                ModelClass todDo = new ModelClass(newTitle, newDesc, newDate,newTime,imageDirectory);
                dbHandler.addToDo(todDo);

//                Log.d("Database", "New To-Do added:");
//                Log.d("Database", "Title: " + newTitle);
//                Log.d("Database", "Description: " + newDesc);
//                Log.d("Database", "Date: " + newDate);
//                Log.d("Database", "Time: " + newTime);
//                Log.d("Database", "Image Directory: " + imageDirectory);

                // Show toast message
                Toast.makeText(context, "To-do added successfully", Toast.LENGTH_SHORT).show();


                startActivity(new Intent(context, MainActivity.class));

            }
        });



//        EditText diaryText = findViewById(R.id.diaryText);
//        Button saveEntryButton = findViewById(R.id.saveEntryButton);

//        saveEntryButton.setOnClickListener(v -> {
//            String text = diaryText.getText().toString();
//            // Here, you would save the text to a database or local storage
//        });
    }

    private void showDatePickerDialog() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog and set date picker listener
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Display selected date in TextView
                        formattedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        selectedDateTextView.setText(formattedDate);
                        heading= formattedDate.concat(" ").concat(currentTime);
                        title.setText(heading);
                    }
                }, year, month, dayOfMonth);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

    private void showTimePickerDialog(){
        // Get current time from Calendar
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the selected time as a String
                        @SuppressLint("DefaultLocale") String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        currentTime=selectedTime;
                        selectedTimeTextView.setText(selectedTime);
                        heading= formattedDate.concat(" ").concat(currentTime);
                        title.setText(heading);
                    }
                }, hour, minute, true); // true indicates 24-hour time format

        // Show the TimePickerDialog
        timePickerDialog.show();
    }

//    private boolean checkPermission() {
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION}, PERMISSION_REQUEST_CODE);
//    }

    private void imageChooser(){
        imagePickerLauncher.launch("image/*");
//        hasImage=true;
//        updateButtonVisibility();
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//
//        startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICTURE);
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == SELECT_PICTURE){
//            imageDirectory = data.getData();
//            if (imageDirectory != null) {
//                imageView.setImageURI(imageDirectory);
//                System.out.println("Image Path: "+imageDirectory.toString());
//            }
//        }
//    }

//    private void updateButtonVisibility() {
//        if (hasImage) {
//            imageRemoveButton.setVisibility(View.VISIBLE);
//        } else {
//            imageRemoveButton.setVisibility(View.INVISIBLE);
//        }

//    private void openImagePicker() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                openImagePicker();
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri selectedImageUri = data.getData();
//            imageView.setImageURI(selectedImageUri);
//        }
//    }

}

