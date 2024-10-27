export const getAvatarColor = (messageSender) => {
  let hash = 0;
  for (let i = 0; i < messageSender.length; i++) {
      hash = 31 * hash + messageSender.charCodeAt(i);
  }
  let index = Math.abs(hash % colors.length);
  return colors[index];
}