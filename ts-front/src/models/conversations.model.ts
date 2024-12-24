
export interface Conversation {
  id: number;
  isGroup: boolean;
  groupMetadata: GroupMetadata | null;
  participants: Participant[];
}


export interface ConversationDTO {
  id: number | null
  name: string | null;
  isGroup: boolean;
  dscription: string | null;
  participants: string[];
}


export interface GroupMetadata {
  id: number;
  name: string;
  description: string;
}

export interface Participant {
  id: number;
  name: string;
  uuid: string;
  username: string;
}