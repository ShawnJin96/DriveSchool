package com.example.driveschool;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

class FireBase
{
	private IFireBase iFireBase;

	private FirebaseAuth mAuth;
	private FirebaseStorage mStorage;

	FireBase(IFireBase iFireBase)
	{
		this.iFireBase = iFireBase;

		mAuth = FirebaseAuth.getInstance();
		mStorage = FirebaseStorage.getInstance();
	}

	boolean isAuthenticated()
	{
		return mAuth.getCurrentUser() != null;
	}

	void createUser(String email, String password)
	{
		mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
		{
			@Override
			public void onComplete(@NonNull Task<AuthResult> task)
			{
				iFireBase.onSignUp(task);
			}
		});
	}

	void signIn(String email, String password)
	{
		mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
		{
			@Override
			public void onComplete(@NonNull Task<AuthResult> task)
			{
				iFireBase.onSignIn(task);
			}
		});
	}

	void signOut()
	{
		mAuth.signOut();
	}

	void get(String file)
	{
		mStorage.getReference().child(file).getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>()
		{
			@Override
			public void onSuccess(byte[] bytes)
			{
				iFireBase.onGetSuccess(bytes);
			}
		}).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception exception)
			{
				iFireBase.onGetFailure(exception);
			}
		});
	}

	void put(String file, byte[] data)
	{
		mStorage.getReference().child(file).putBytes(data).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception exception)
			{
				iFireBase.onPutFailure(exception);
			}
		}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
		{
			@Override
			public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
			{
				iFireBase.onPutSuccess();
			}
		});
	}
}