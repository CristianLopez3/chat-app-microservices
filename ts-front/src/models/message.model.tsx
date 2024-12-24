
export interface MessageResponse {
  conversationId: string;
  senderId: string;
  content: string;
  createdAt: Date;
}


export interface MessageRequest {
  conversationId: string;
  senderId: string;
  content: string;
}
