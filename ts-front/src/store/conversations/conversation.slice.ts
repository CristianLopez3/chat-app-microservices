// src/store/userSlice.ts
import { createSlice } from '@reduxjs/toolkit';
import { ChatPayload, Conversation, UserResponse } from '@/models';
import { createConversationAction, getUserConversationsAction, selectChatAction } from './conversation.action';

interface ConversationState {
  isLoading: boolean;
  user: UserResponse | null;
  conversations: Conversation[];
  selectedConversation: Conversation | null;
  messages: ChatPayload[];
  error: {
    message: string;
    status: number;
  } | null;
}

const initialState: ConversationState = {
  user: null,
  isLoading: false,
  conversations: [],
  selectedConversation: null,
  messages: [],
  error: null,
};

const conversationSlice = createSlice({
  name: 'conversation',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      /** CREATE CONVERSTION STATE */
      .addCase(createConversationAction.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(createConversationAction.fulfilled, (state, action) => {
        state.isLoading = false;
        state.conversations = [...state.conversations, action.payload];
      })
      .addCase(createConversationAction.rejected, (state, action) => {
        console.log(action.payload);
        state.isLoading = false;
        state.error = action.payload as { message: string; status: number; };
      })

      /** GET CONVERSATIONS */
      .addCase(getUserConversationsAction.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getUserConversationsAction.fulfilled, (state, action) => {
        state.isLoading = false;
        state.conversations = action.payload;
      })
      .addCase(getUserConversationsAction.rejected, (state, action) => {
        console.log(action.payload);
        state.isLoading = false;
        state.error = action.payload as { message: string; status: number; };
      })

      /** SET SELECTED CONVERSATION */
      .addCase(selectChatAction.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(selectChatAction.fulfilled, (state, action) => {
        console.log("Set selected chat to: " + action.payload.id);
        state.isLoading = false;
        state.selectedConversation = action.payload;
      })
      .addCase(selectChatAction.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload as { message: "Fail trying to select the chat"; status: 400; };
      })

      ;
  },
});

export default conversationSlice.reducer;