// src/store/userSlice.ts
import { createSlice } from '@reduxjs/toolkit';
import {  loginUserAction, signUpUserAction } from './user.action';
import { UserResponse } from '@/models';

interface UserState {
  isLoading: boolean;
  user: UserResponse | null;
  error: string | null;
}

const initialState: UserState = {
  user: null,
  isLoading: false,
  error: null,
};

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    logoutUser(state) {
      state.user = null;
      state.isLoading = false;
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      /** LOGIN CASE */
      .addCase(loginUserAction.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(loginUserAction.fulfilled, (state, action) => {
        state.isLoading = false;
        state.user = action.payload;
      })
      .addCase(loginUserAction.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload as string;
      })
      /** SIGN UP CASE */
      .addCase(signUpUserAction.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(signUpUserAction.fulfilled, (state, action) => {
        state.isLoading = false;
        state.user = action.payload;
      })
      .addCase(signUpUserAction.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload as string;
      })

      ;
  },
});

export const { logoutUser } = userSlice.actions;
export default userSlice.reducer;