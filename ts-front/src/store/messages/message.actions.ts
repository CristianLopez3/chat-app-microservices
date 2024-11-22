// src/store/message.actions.ts
import { createAsyncThunk } from '@reduxjs/toolkit';
import { RootState } from '../store';

export const addMessage = createAsyncThunk(
  'user/addMessage',
  async (message: any, { getState }) => {
    const state = getState() as RootState;
    const currentMessages = state.messages.messages || [];
    return [...currentMessages, message];
  }
);