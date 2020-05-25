package com.example.driveschool;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface IFireBase
{
	void onSignUp(Task<AuthResult> task);
	void onSignIn(Task<AuthResult> task);

	void onGetSuccess(byte[] data);
	void onGetFailure(Exception exception);

	void onPutSuccess();
	void onPutFailure(Exception exception);
}