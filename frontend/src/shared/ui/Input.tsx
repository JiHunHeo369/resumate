import React from 'react';

type InputProps = React.InputHTMLAttributes<HTMLInputElement>;

export const Input = React.forwardRef<HTMLInputElement, InputProps>(
  ({ className, ...props }, ref) => {
    return (
      <input
        ref={ref}
        className={`border px-3 py-2 rounded-md outline-none focus:ring-2 focus:ring-blue-500 ${className ?? ''}`}
        {...props}
      />
    );
  }
);

Input.displayName = 'Input';