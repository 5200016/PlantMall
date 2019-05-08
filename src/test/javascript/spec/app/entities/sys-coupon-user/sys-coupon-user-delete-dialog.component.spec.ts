/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponUserDeleteDialogComponent } from 'app/entities/sys-coupon-user/sys-coupon-user-delete-dialog.component';
import { SysCouponUserService } from 'app/entities/sys-coupon-user/sys-coupon-user.service';

describe('Component Tests', () => {
    describe('SysCouponUser Management Delete Component', () => {
        let comp: SysCouponUserDeleteDialogComponent;
        let fixture: ComponentFixture<SysCouponUserDeleteDialogComponent>;
        let service: SysCouponUserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponUserDeleteDialogComponent]
            })
                .overrideTemplate(SysCouponUserDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponUserDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponUserService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
