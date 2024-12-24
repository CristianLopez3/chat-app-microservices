
export interface ChatMember {
  id: string;
  name: string;
  lastMessage: string;
  selected?: boolean;
}

export interface ChatUserData {
  senderId: string;
  conversationId: string | number;
  message: string;
  connected: boolean;
}

export interface ChatPayload{
  senderId: string;
  conversationId: string;
  message: string;
  status: string;
}