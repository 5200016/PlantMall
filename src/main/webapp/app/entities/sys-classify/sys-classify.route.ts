import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from './sys-classify.service';
import { SysClassifyComponent } from './sys-classify.component';
import { SysClassifyDetailComponent } from './sys-classify-detail.component';
import { SysClassifyUpdateComponent } from './sys-classify-update.component';
import { SysClassifyDeletePopupComponent } from './sys-classify-delete-dialog.component';
import { ISysClassify } from 'app/shared/model/sys-classify.model';

@Injectable({ providedIn: 'root' })
export class SysClassifyResolve implements Resolve<ISysClassify> {
    constructor(private service: SysClassifyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysClassify> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysClassify>) => response.ok),
                map((sysClassify: HttpResponse<SysClassify>) => sysClassify.body)
            );
        }
        return of(new SysClassify());
    }
}

export const sysClassifyRoute: Routes = [
    {
        path: 'sys-classify',
        component: SysClassifyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-classify/:id/view',
        component: SysClassifyDetailComponent,
        resolve: {
            sysClassify: SysClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-classify/new',
        component: SysClassifyUpdateComponent,
        resolve: {
            sysClassify: SysClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-classify/:id/edit',
        component: SysClassifyUpdateComponent,
        resolve: {
            sysClassify: SysClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysClassifyPopupRoute: Routes = [
    {
        path: 'sys-classify/:id/delete',
        component: SysClassifyDeletePopupComponent,
        resolve: {
            sysClassify: SysClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysClassify.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
