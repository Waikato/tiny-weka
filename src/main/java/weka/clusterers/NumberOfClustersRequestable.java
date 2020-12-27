/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 *    NumberOfClustersRequestable.java
 *    Copyright (C) 2004-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.clusterers;

/**
 * Interface to a clusterer that can generate a requested number of
 * clusters
 *
 * @author Mark Hall
 * @version $Revision: 8034 $
 */
public interface NumberOfClustersRequestable {
  
  /**
   * Set the number of clusters to generate
   *
   * @param numClusters the number of clusters to generate
   * @exception Exception if the requested number of 
   * clusters in inapropriate
   */
  void setNumClusters(int numClusters) throws Exception;

}

