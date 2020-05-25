package com.example.driveschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements IFireBase
{
	private FireBase fireBase;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FirebaseApp.initializeApp(getBaseContext());
		fireBase = new FireBase(this);

		fireBase.createUser("helloworld@java.com", "me");
	}

	@Override
	public void onSignUp(Task<AuthResult> task)
	{
		if (task.isSuccessful())
		{
			Toast.makeText(getBaseContext(), "SignUp : Success", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(getBaseContext(), "SignUp : Fail", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onSignIn(Task<AuthResult> task)
	{
		if (task.isSuccessful())
		{
			Toast.makeText(getBaseContext(), "SignIn : Success", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(getBaseContext(), "SigIn : Fail", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onGetSuccess(byte[] data)
	{

	}

	@Override
	public void onGetFailure(Exception exception)
	{

	}

	@Override
	public void onPutSuccess()
	{

	}

	@Override
	public void onPutFailure(Exception exception)
	{

	}
}
