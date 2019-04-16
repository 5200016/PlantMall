import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysCollection } from 'app/shared/model/sys-collection.model';
import { SysCollectionService } from './sys-collection.service';
import { SysCollectionComponent } from './sys-collection.component';
import { SysCollectionDetailComponent } from './sys-collection-detail.component';
import { SysCollectionUpdateComponent } from './sys-collection-update.component';
import { SysCollectionDeletePopupComponent } from './sys-collection-delete-dialog.component';
import { ISysCollection } from 'app/shared/model/sys-collection.model';

@Injectable({ providedIn: 'root' })
export class SysCollectionResolve implements Resolve<ISysCollection> {
    constructor(private service: SysCollectionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysCollection> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysCollection>) => response.ok),
                map((sysCollection: HttpResponse<SysCollection>) => sysCollection.body)
            );
        }
        return of(new SysCollection());
    }
}

export const sysCollectionRoute: Routes = [
    {
        path: 'sys-collection',
        component: SysCollectionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCollection.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-collection/:id/view',
        component: SysCollectionDetailComponent,
        resolve: {
            sysCollection: SysCollectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCollection.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-collection/new',
        component: SysCollectionUpdateComponent,
        resolve: {
            sysCollection: SysCollectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCollection.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-collection/:id/edit',
        component: SysCollectionUpdateComponent,
        resolve: {
            sysCollection: SysCollectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCollection.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysCollectionPopupRoute: Routes = [
    {
        path: 'sys-collection/:id/delete',
        component: SysCollectionDeletePopupComponent,
        resolve: {
            sysCollection: SysCollectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCollection.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
