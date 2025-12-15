import { ReactNode } from 'react';
import { ClientProviders } from './ClientProvider';

export const AppProvider = ({ children }: { children: ReactNode }) => {
  return <ClientProviders>{children}</ClientProviders>;
};
