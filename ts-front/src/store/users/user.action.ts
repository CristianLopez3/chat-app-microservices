// src/store/userActions.ts
import { createAsyncThunk } from '@reduxjs/toolkit';
import { login, signUp } from '@/services';
import { User, UserLogin, UserResponse } from '@/models';

export const loginUserAction = createAsyncThunk<UserResponse, UserLogin>(
  'user/loginUser',
  async (credentials, { rejectWithValue }) => {
    try {
      const { call } = login(credentials);
      const response = await call;
      return response.data;
    } catch (error: any) {
      if (!error.response) {
        throw error;
      }
      return rejectWithValue(error.response.data);
    }
  }
);

export const signUpUserAction = createAsyncThunk<UserResponse, User>(
  "user/signUpUser",
  async (user, { rejectWithValue }) => {
    try {
      const { call } = signUp(user);
      const response = await call;
      return response.data;
    } catch (error: any) {
      if (!error.response) {
        throw error;
      }
      return rejectWithValue(error.response.data);
    }
  }
)