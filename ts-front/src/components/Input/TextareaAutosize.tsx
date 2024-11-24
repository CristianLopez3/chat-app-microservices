import React from 'react';
import { TextField, TextareaAutosize } from '@mui/material';

type AutoResizeTextFieldProps = {
  value: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
  placeholder?: string;
};

export const AutoResizeTextField: React.FC<AutoResizeTextFieldProps> = ({ value, onChange, placeholder }) => {
  return (
    <TextField
      fullWidth
      id="message"
      type="text"
      className="input-message"
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      variant="outlined"
      multiline
      sx={{
        resize: 'none',
        overflow: 'hidden',
        minHeight: '3em',
      }}
    />
  );
};