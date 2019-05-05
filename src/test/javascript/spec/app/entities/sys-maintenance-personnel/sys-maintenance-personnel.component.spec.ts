/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenancePersonnelComponent } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel.component';
import { SysMaintenancePersonnelService } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel.service';
import { SysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';

describe('Component Tests', () => {
    describe('SysMaintenancePersonnel Management Component', () => {
        let comp: SysMaintenancePersonnelComponent;
        let fixture: ComponentFixture<SysMaintenancePersonnelComponent>;
        let service: SysMaintenancePersonnelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenancePersonnelComponent],
                providers: []
            })
                .overrideTemplate(SysMaintenancePersonnelComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMaintenancePersonnelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMaintenancePersonnelService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysMaintenancePersonnel(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysMaintenancePersonnels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
