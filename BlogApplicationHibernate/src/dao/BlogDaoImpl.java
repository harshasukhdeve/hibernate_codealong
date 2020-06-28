package dao;

import java.sql.SQLException;
import java.util.List;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import org.hibernate.*;


import model.Blog;
import utility.HibernateConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface{

	private SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

	
	@Override
	public void insertBlog(Blog blog) throws SQLException, Exception {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(blog);
		transaction.commit();
		session.close();
		
	}

	@Override
	public Blog selectBlog(int blogId) throws Exception {
		Session session = this.sessionFactory.openSession();
		Blog blog = (Blog) session.get(Blog.class, blogId);
		session.close();
		return blog;
	}

//	@Override
//	public List<Blog> selectAllBlogs() throws Exception {     // this function only display the data right...yes  // which version hibernate u use 5.2   as sir has suggested
//		Session session = this.sessionFactory.openSession();
//		try
//		{
//		
//		CriteriaBuilder builder=  session.getCriteriaBuilder();
//		CriteriaQuery<Blog> query=builder.createQuery(Blog.class);
//		Root<Blog> root=query.from(Blog.class);
//		query.select(root);
//		Query<Blog> q=session.createQuery(query);
//		List<Blog> listblog=q.getResultList();
//		return listblog;
//		}
//		catch(Exception e)
//		{
//			
//		}
//	}
//	
	
	@Override
	public List<Blog> selectAllBlogs() {
		 Session session = this.sessionFactory.openSession();
		 CriteriaBuilder builder = session.getCriteriaBuilder();
		 CriteriaQuery<Blog> query = builder.createQuery(Blog.class);
		  Root<Blog> root = query.from(Blog.class);
		  query.select(root);
		  Query<Blog> q=session.createQuery(query);
		//Criteria cr = session.createCriteria(Blog.class);
		 List<Blog> listBlog = q.getResultList();
		return listBlog;
	}

	@Override
	public void deleteBlog(int id) throws SQLException, Exception {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Blog blog=(Blog) session.get(Blog.class,id);
		session.delete(blog);
		transaction.commit();
		session.close();
	}

	@Override
	public void updateBlog(Blog blog) throws SQLException, Exception {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(blog);
		transaction.commit();
		session.close();
	}
	
	
}