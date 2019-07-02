import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';
import { SysMaintenanceFinishService } from './sys-maintenance-finish.service';

@Component({
    selector: 'jhi-sys-maintenance-finish-delete-dialog',
    templateUrl: './sys-maintenance-finish-delete-dialog.component.html'
})
export class SysMaintenanceFinishDeleteDialogComponent {
    sysMaintenanceFinish: ISysMaintenanceFinish;

    constructor(
        protected sysMaintenanceFinishService: SysMaintenanceFinishService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysMaintenanceFinishService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysMaintenanceFinishListModification',
                content: 'Deleted an sysMaintenanceFinish'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-maintenance-finish-delete-popup',
    template: ''
})
export class SysMaintenanceFinishDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMaintenanceFinish }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysMaintenanceFinishDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysMaintenanceFinish = sysMaintenanceFinish;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
