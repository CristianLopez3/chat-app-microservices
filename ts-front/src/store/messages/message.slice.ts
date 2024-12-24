import { createSlice } from '@reduxjs/toolkit';
import { addMessage } from './message.actions';
import { ChatPayload } from '@/models';

interface MessageState {
  isLoading: boolean;
  messages: Record<string | number, ChatPayload[]>;
  error: string | null;
}

const initialState: MessageState = {
  messages: {},
  isLoading: false,
  error: null,
};

const messageSlice = createSlice({
  name: 'messages',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addMessage.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(addMessage.fulfilled, (state, action) => {
        state.isLoading = false;
        const { conversationId } = action.payload;
        if (!state.messages[conversationId]) {
          state.messages[conversationId] = [];
        }
        state.messages[conversationId].push(action.payload);
      })
      .addCase(addMessage.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message || 'Failed to add message';
      });
  },
});

export default messageSlice.reducer;