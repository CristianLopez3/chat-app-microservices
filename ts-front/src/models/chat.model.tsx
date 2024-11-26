
export interface ChatMember {
  id: string;
  name: string;
  lastMessage: string;
  selected?: boolean;
}

export interface ChatUserData {
  senderId: string;
  receiverId: string;
  message: string;
  connected: boolean;
}

export interface ChatPayload{
  senderId: string;
  receiverId: string;
  message: string;
  status: string;
}