
export interface UserLogin {
  username: string;
  password: string;
}

export interface User {
  userId: string | null;
  name: string;
  username: string;
  password: string;
}

export type UserResponse = {
  userId: string | null;
  uuid: string;
  name: string;
  username: string;
  selected?: boolean;
}