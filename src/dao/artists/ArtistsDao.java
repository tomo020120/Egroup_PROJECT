package dao.artists;

import java.util.List;

import bean.ArtistBean;
import bean.joinBean.AllArtistDetailBean;


public interface ArtistsDao {
	public List<ArtistBean> getAllArtists();
	public List<AllArtistDetailBean> getArtistsDetails(String artistId);
}
