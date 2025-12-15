import { AppProvider } from '@/app/index';

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <html lang='ko'>
      <body cz-shortcut-listen='true'>
        <AppProvider>
          <main>{children}</main>
        </AppProvider>
      </body>
    </html>
  );
}
