package sk.upjs.ics.paz1c.mp3library;

public interface SongImporterListener {
    public void started();
    public void statusChanges(String message);
    public void finished();
}
