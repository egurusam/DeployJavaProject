package github;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class A {
	
 public static void main(String[] args) throws IOException {
	

	GitHubClient client = new GitHubClient();
    //client.setOAuth2Token(token);

        RepositoryService repoService = new RepositoryService(client);
       client.setCredentials("master", ""); 
        for (Repository repo : repoService.getRepositories("master"))
        	  System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
    try {
        Repository repo = repoService.getRepository("master", "algo");

        
        ContentsService contentService = new ContentsService(client);
        List<RepositoryContents> test = contentService.getContents(repo, "algo/configs/");

       // List<RepositoryContents> contentList = contentService.getContents(repo);
        for(RepositoryContents content : test){
            String fileConent = content.getContent();
            String valueDecoded= new String(Base64.decodeBase64(fileConent.getBytes() ));
            System.out.println(valueDecoded);
        }

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}
