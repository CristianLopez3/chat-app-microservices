
export interface ChatMember {
  id: string;
  name: string;
  lastMessage: string;
  selected?: boolean;
}

export interface ChatUserData {
  username: string;
  recievername: string;
  message: string;
  connected: boolean;
}

export interface ChatPayload{
  senderName: string;
  receiverName: string;
  message: string;
  status: string;
}
