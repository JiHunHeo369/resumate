import { Nav } from '@/shared/ui';

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <Nav />

      <main
        style={{ margin: 0, padding: 0 }}
        className='flex justify-center items-center'
      >
        {children}
      </main>
    </>
  );
}
