export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en"> 
      <body>
        <header>
          <h1>My Application</h1>
        </header>
        <main>{children}</main>
        <footer>
          <p>© 2024 My Application</p>
        </footer>
      </body>
    </html>
  )
}